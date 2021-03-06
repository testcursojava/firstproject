package model;


public class contador extends Thread{
	private boolean _exec;
	 	private int _actual;
	 	private long _sleep;
	 	private String _tag;
	 	private OnUpdate _listener;
	 	
	 	
		public contador(String tag,OnUpdate listener) {
	 		_tag = tag;
	 		_exec = false;
	 		_sleep = 1000;
	 		_actual = 0;
	 		_listener = listener;
	 	}
		
		public contador(String tag,OnUpdate listener, long sleep) {
	 		_tag = tag;
	 		_exec = false;
	 		_sleep = sleep;
	 		_actual = 0;
	 		_listener = listener;
	 	}
	 	
		public void start() {
			new Thread() {
				public void run() {
				 	
			 		while(_exec) {
			 			try {
			 				sleep(_sleep);
			 			} catch (InterruptedException e) {
			 			}
			 			_actual++;
			 			_listener.onupdate(_tag, _actual);
			 		}
			 	}
			}.start();
		}
	 	
	 	
	 	public synchronized void exec() {
	 		if(!_exec) {
	 			_actual = 0;
	 			_exec = true;
	 			start();
	 		}
	 	}
	 	
	 	public synchronized void para() {
	 		_exec = false;
	 	}
	 	
	 	public boolean isExec() {
	 		return _exec;
	 	}
	 	
	 	public String getTag() {
	 		return _tag;
	 	}
	 	
	 	public long getSleep() {
	 		return _sleep;
	 	}
	 	
	 	public int getActual() {
	 		return _actual;
	 	}
	 	
	 	public interface OnUpdate {
	 		public void onupdate(String tag,int actual);
	 	}
	 	
	 	public void setSleep(long v) {
	 		_sleep= v;
	 	}
	 	
	 
}

