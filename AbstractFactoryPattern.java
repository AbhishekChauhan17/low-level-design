//Abstract Factory Pattern Question
//Problem:
//You are building a cross-platform UI library that supports multiple operating systems (Windows, macOS, and Linux).
//Each OS has different UI components like buttons and checkboxes, and you need a system that dynamically creates the appropriate components based on the OS.

enum OperatingSystemType {
	MAC,
	WINDOWS,
	LINUX
}

enum ComponentType {
	BUTTON,
	CHECKBOX,
	FORM
}

interface ComponentInterface {
	public void printComponentInfo();
}

class WindowsButtonComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Windows Button");
	}
}

class MACButtonComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("MAC Button");
	}
}

class LinuxButtonComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Linux Button");
	}
}

class WindowsCheckboxComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Windows Checkbox");
	}
}

class MACCheckboxComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("MAC Checkbox");
	}
}

class LinuxCheckboxComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Linux Checkbox");
	}
}

class WindowsFormComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Windows Form");
	}
}

class MACFormComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("MAC Form");
	}
}

class LinuxFormComponent implements ComponentInterface {
	public void printComponentInfo() {
		System.out.println("Linux Form");
	}
}

interface OperatingSystemComponentFactoryInterface {
	public ComponentInterface getComponent(ComponentType componentType) throws Exception;
}

class WindowsOperatingSystemComponentFactory implements OperatingSystemComponentFactoryInterface {
	public ComponentInterface getComponent(ComponentType componentType) throws Exception {
		if (componentType == null) throw new Exception("Invalid Component Type");
		return switch(componentType) {
			case BUTTON -> new WindowsButtonComponent();
			case CHECKBOX -> new WindowsCheckboxComponent();
			case FORM -> new WindowsFormComponent();
			default -> new WindowsButtonComponent();
		};
	}
}

class MACOperatingSystemComponentFactory implements OperatingSystemComponentFactoryInterface {
	public ComponentInterface getComponent(ComponentType componentType) throws Exception {
		if (componentType == null) throw new Exception("Invalid Component Type");
		return switch(componentType) {
			case BUTTON -> new MACButtonComponent();
			case CHECKBOX -> new MACCheckboxComponent();
			case FORM -> new MACFormComponent();
			default -> new MACButtonComponent();
		};
	}
}

class LinuxOperatingSystemComponentFactory implements OperatingSystemComponentFactoryInterface {
	public ComponentInterface getComponent(ComponentType componentType) throws Exception {
		if (componentType == null) throw new Exception("Invalid Component Type");
		return switch(componentType) {
			case BUTTON -> new LinuxButtonComponent();
			case CHECKBOX -> new LinuxCheckboxComponent();
			case FORM -> new LinuxFormComponent();
			default -> new LinuxButtonComponent();
		};
	}
}

interface OperatingSystemFactoryInterface {
	public OperatingSystemComponentFactoryInterface getOperatingSystem(OperatingSystemType operatingSystemType) throws Exception;
}

class OperatingSystemComponentFactory implements OperatingSystemFactoryInterface {
	public OperatingSystemComponentFactoryInterface getOperatingSystem(OperatingSystemType operatingSystemType) throws Exception{
		if (operatingSystemType == null) throw new Exception("Invalid Operating System Type");
		return switch(operatingSystemType) {
			case WINDOWS -> new WindowsOperatingSystemComponentFactory();
			case MAC -> new MACOperatingSystemComponentFactory();
			case LINUX -> new LinuxOperatingSystemComponentFactory();
			default -> new WindowsOperatingSystemComponentFactory();
		};
	}
}

public class AbstractFactoryPattern {
	public static void main(String []args) throws Exception{
		OperatingSystemFactoryInterface operatingSystemComponentFactory = new OperatingSystemComponentFactory();
		OperatingSystemComponentFactoryInterface windowsOperatingSystemComponentFactory = operatingSystemComponentFactory.getOperatingSystem(OperatingSystemType.WINDOWS);
		OperatingSystemComponentFactoryInterface macOperatingSystemComponentFactory = operatingSystemComponentFactory.getOperatingSystem(OperatingSystemType.MAC);
		OperatingSystemComponentFactoryInterface linuxOperatingSystemComponentFactory = operatingSystemComponentFactory.getOperatingSystem(OperatingSystemType.LINUX);
		ComponentInterface button = windowsOperatingSystemComponentFactory.getComponent(ComponentType.BUTTON);
		ComponentInterface checkbox = macOperatingSystemComponentFactory.getComponent(ComponentType.CHECKBOX);
		ComponentInterface form  = linuxOperatingSystemComponentFactory.getComponent(ComponentType.FORM);
		button.printComponentInfo();
		checkbox.printComponentInfo();
		form.printComponentInfo();
	}
}