//Create a File interface with methods:
//
//readData(): Returns the file content as a string.
//writeData(String data): Writes the given data to the file.
//Implement a PlainFile class that serves as a base file.
//
//Stores and returns raw data without compression.
//Create decorators to add compression functionality:
//
//ZipCompressionDecorator → Compresses the data using a simple zip-like algorithm (e.g., replacing duplicate characters).
//GzipCompressionDecorator → Applies gzip-like compression (e.g., removing spaces or simple encoding).
//Allow multiple compression techniques to be applied together.

interface TextInterface {
	public String getText();
}

class PlainText implements TextInterface {
	String text;
	PlainText(String text) {
		this.text = text;
	}
	public String getText() {
		return this.text;
	}
}

abstract class TextCompressor implements TextInterface {
	TextInterface text;
	TextCompressor(TextInterface text) {
		this.text = text;
	}
	public String getText() {
		return this.text.getText();
	}
}

class SpaceRemoveCompressor extends TextCompressor {
	SpaceRemoveCompressor(TextInterface text) {
		super(text);
	}
	public String getText() {
		return text.getText().replace(String.valueOf(' '), "");
	}
}

class ExclamationRemoveCompressor extends TextCompressor {
	ExclamationRemoveCompressor(TextInterface text) {
		super(text);
	}
	public String getText() {
		return text.getText().replace(String.valueOf('!'), "");
	}
}

class QuestionMarkRemoveCompressor extends TextCompressor {
	QuestionMarkRemoveCompressor(TextInterface text) {
		super(text);
	}
	public String getText() {
		return text.getText().replace(String.valueOf('?'), "");
	}
}

public class DecoratorDesignPattern {
	public static void main(String []args) {
		String plainText = "This is the sample text that exists over here !!!!????";
		TextInterface text = new PlainText(plainText);
		TextInterface spaceRemovedText = new SpaceRemoveCompressor(text);
		System.out.println(spaceRemovedText.getText());
		TextInterface spaceExclamationRemovedText = new ExclamationRemoveCompressor(new SpaceRemoveCompressor(text));
		System.out.println(spaceExclamationRemovedText.getText());
		TextInterface spaceExclamationQuestionMarkRemovedText = new QuestionMarkRemoveCompressor(new ExclamationRemoveCompressor(spaceRemovedText));
		System.out.println(spaceExclamationQuestionMarkRemovedText.getText());
	}
}


//2. Text Formatter
//Create a Text interface with a format() method.
//Implement a PlainText class that simply returns the text.
//Create decorators for:
//BoldDecorator → Wraps text in "<b>...</b>".
//ItalicDecorator → Wraps text in "<i>...</i>".
//UnderlineDecorator → Wraps text in "<u>...</u>".

//interface TextInterface {
//	public abstract String getText();
//}
//
//class Text implements TextInterface {
//	String text;
//	Text (String text) {
//		this.text = text;
//	}
//	public String getText() {
//		return text;
//	}
//}
//
//abstract class TextDecorator implements TextInterface {
//	TextInterface text;
//	public TextDecorator(TextInterface text) {
//		this.text = text;
//	}
//	public String getText() {
//		return text.getText();
//	}
//}
//
//class Underline extends TextDecorator {
//	public Underline(TextInterface text) {
//		super(text);
//	}
//	public String getText() {
//		return "<u>" + super.getText() + "</u>";
//	}
//}
//
//class Bold extends TextDecorator {
//	public Bold(TextInterface text) {
//		super(text);
//	}
//	public String getText() {
//		return "<b>" + super.getText() + "</b>";
//	}
//}
//
//class Italic extends TextDecorator {
//	public Italic(TextInterface text) {
//		super(text);
//	}
//	public String getText() {
//		return "<i>" + super.getText() + "</i>";
//	}
//}
//
//public class DecoratorDesignPattern {
//	public static void main(String []args) {
//		TextInterface tinterface = new Text("apple");
//		TextInterface boldText = new Bold(tinterface);
//		System.out.println(boldText.getText());
//		TextInterface boldUnderlineText = new Underline(new Bold(tinterface));
//		System.out.println(boldUnderlineText.getText());
//		TextInterface italicBoldUnderlineText = new Italic(new Underline(boldText));
//		System.out.println(italicBoldUnderlineText.getText());
//	}
//}