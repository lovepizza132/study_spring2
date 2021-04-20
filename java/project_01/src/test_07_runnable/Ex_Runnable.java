package test_07_runnable;

public class Ex_Runnable {
	static class MyRunnable implements Runnable{
		@Override
		public void run() {
			System.out.println("========== Thread start ... ==========");
			long randomNumber = Math.round(Math.random()*10) * 1000;
			System.out.println("randomNumber : " + randomNumber/1000);
			try {
				Thread.sleep(randomNumber);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("========== Thread end ... ==========");
		}
		
	}
	
	public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        
        Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("========== Thread2 start ... ==========");
				long randomNumber = Math.round(Math.random()*10) * 1000;
				System.out.println("randomNumber2 : " + randomNumber/1000);
				try {
					Thread.sleep(randomNumber);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("========== Thread2 end ... ==========");				
			}
		});
        thread2.start();
        
        Thread thread3 = new Thread(
        		() -> {
    				System.out.println("========== Thread3 start ... ==========");
    				long randomNumber = Math.round(Math.random()*10) * 1000;
    				System.out.println("randomNumber3 : " + randomNumber/1000);
    				try {
    					Thread.sleep(randomNumber);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    				System.out.println("========== Thread3 end ... ==========");	
        		});
        thread3.start();
	}
}
