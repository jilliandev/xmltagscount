package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;

public class Main {

	public static void main(String[] args) throws IOException {

		String configFileName = "somefile";

		//for Unix - "/"
		String directorySeparator = System.getProperty("file.separator");

		File currentDirectory = new File(".");

		String filePath = currentDirectory.getCanonicalPath() + directorySeparator + configFileName;

		try {
			//Get the parser, provides to work with file as DOM object tree
			DocumentBuilderFactory parser = DocumentBuilderFactory.newInstance();

			Document parsedFile = parser.newDocumentBuilder().parse(filePath);

			System.out.print("Enter the name of the tag: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String tagName = reader.readLine();

			NodeList nodes = parsedFile.getElementsByTagName(tagName);

			System.out.println("\nTotal counts of this tag: \"" + nodes.getLength() + "\"");

			//Values of tag
			//---------------------------------------------------------------------------------------------------------
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.getLength() > 0) {
					NodeList subList = nodes.item(i).getChildNodes();
					if (!subList.item(0).getNodeValue().trim().isEmpty() && subList.getLength() > 0) {
						System.out.println(i + 1 + " value of this tag: \"" + subList.item(0).getNodeValue() + "\"");
					} else {
						System.out.println("There is no values for this tag");
					}
				}
			}
			//---------------------------------------------------------------------------------------------------------
		}
		//Handling exceptions in the case of not finding the file
		catch (FileNotFoundException e) {
			System.out.println("File not found!");

			e.printStackTrace();
		}
		//Handling exceptions when an error occurs IO
		catch (IOException e) {
			System.out.println("IO Error!");

			e.printStackTrace();
		}
		//Other exceptions
		catch (Exception e) {
			System.out.println("Error!");

			e.printStackTrace();
		}
	}
}
