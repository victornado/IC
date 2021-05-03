package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

import Modelo.ID3;

public class MAIN {

	public static void main(String[] args) {

		//Controller c =new Controller();
		try {
			ID3 e= new ID3();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
