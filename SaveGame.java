import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveGame {

	public static final String SaveFile = "TestSave.sav"; // name of Save file

	public static void save(Serializable objectToSave) { // save method

		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(SaveFile);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objectToSave); // write to file
			oos.flush();
			oos.close(); // close resource
			System.out.println("Saving....");
		} catch (IOException e) {
			// handle exception
			e.printStackTrace();
		}
	}

	public static ViewLudo load() {  //load method

		if (checkFileExists()) {   //if save file exists load its content 

			FileInputStream fis = null;
			ViewLudo loadedObject = null;
			try {

				fis = new FileInputStream(SaveFile);

				ObjectInputStream ois = new ObjectInputStream(fis);

				loadedObject =(ViewLudo) ois.readObject();  //load content into a viewludo object
				//System.out.println(loadedObject);
				ois.close();

				System.out.println("Loaded");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			return loadedObject;  //return the viewludo object
		}
		return null;
	}

	public static boolean checkFileExists() {  //check if a save file exists

		return new File(SaveFile).isFile();
	}
}
