/*
 * Copyright (C) 2014 Adam Huang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.io;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;

/**
 * // TODO implements Mission
 * @author Adam Huang
 */
public class CopyTask implements Runnable {
	private byte[] mBuffer;
	private InputStream mInput;
	private OutputStream mOutput;
	private boolean mCancel;
	private boolean mComplete;
	private boolean mNeedClose;
	private Collection<StepListener> mStepListeners;
	private Collection<CancelListener> mCancelListeners;

	public CopyTask(InputStream input, OutputStream output) {
		mBuffer = new byte[1024];
		mStepListeners = new HashSet<StepListener>();
		mCancelListeners = new HashSet<CancelListener>();
		mInput = input;
		mOutput = output;
		mNeedClose = true;
	}

	public InputStream getInputStream() {
		return mInput;
	}

	public OutputStream getOutputStream() {
		return mOutput;
	}

	public void setBuffer(byte[] buffer) {
		mBuffer = buffer;
	}

	public void setClose(boolean close) {
		mNeedClose = close;
	}

	public void cancel() {
		if(mComplete)
			return;
		mCancel = true;
		for (CancelListener listener : mCancelListeners) {
			listener.onCancel();
		}
	}

	public void close() throws IOException {
		if(mInput == null || mOutput == null)
			return;
		mOutput.close();
		mInput.close();
	}

	public void addCancelListener(CancelListener listener) {
		mCancelListeners.add(listener);
	}

	public void addStepListener(StepListener listener) {
		if (listener != null)
			mStepListeners.add(listener);
	}

	public void transport() throws IOException {
		while(stepWrite());
	}

	@Override
	public void run() {
		try {
			transport();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isComplete() {
		return mComplete;
	}

	public boolean isCancelled() {
		return mCancel;
	}

	public boolean stepWrite() throws IOException {
		if(mInput == null || mOutput == null)
			return false;
		if (mCancel) {
			if (mNeedClose)
				close();
			return false;
		}
		int length = mInput.read(mBuffer);
		if(length != -1) {
			mComplete = false;
			mOutput.write(mBuffer, 0, length);
			for (StepListener listener : mStepListeners) {
				listener.onStep(length);
			}
			return true;
		}
		if (mNeedClose)
			close();
		mComplete = true;
		return false;
	}
}
