package com.example.factory;

import java.awt.Image;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class NotSupported implements ImageReader {

	public Image loadImage() {
		System.out.println("This format is not supported yet!");
		return null;
	}

}
