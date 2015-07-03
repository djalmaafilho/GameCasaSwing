import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;


public class TesteSom {

	
	public static void main(String[] args) {
		try {
//			AudioClip ac = Applet.newAudioClip(new File("assets/explosion.wav").toURL());
			AudioClip ac = Applet.newAudioClip(new File("assets/music.mid").toURL());
			int i = 0;
			ac.play();
			ac.loop();
			
			while(true){
				try {
					System.out.println("....");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
