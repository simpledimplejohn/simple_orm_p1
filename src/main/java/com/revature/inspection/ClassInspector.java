package com.revature.inspection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class ClassInspector {
	

	
////MY METHOD WORKS //////////////////////
	public String getClassTableName(Class<?> clazz)  {
		
		String tableValue = "";
		// gets the annotation name
		for(Annotation annotation : clazz.getAnnotations()) {
			Class<? extends Annotation> type = annotation.annotationType();
//			System.out.println("Vlaues of " + type.getName());
			// gets the annotation value
			for(Method method : type.getDeclaredMethods()) {
				Object value;
				try {
					value = method.invoke(annotation, (Object[])null);
//					System.out.println(" " + method.getName() + ": " + value);
					tableValue = (String) value;
					return tableValue;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		return null;
		
	}
///// MY SECOND METHOD FOR FIELDS //////////////////////////////////////////////////
	public static Field[] listFields(Class<?> clazz) {
		
		

		Field[] fields = clazz.getDeclaredFields(); // comes from lava.lang.reflext.Field

		if (fields.length == 0) {
			System.out.println("\nThere are no non public fields in " + clazz.getName());
		}

		for (Field field : fields) {
			if ((field.getModifiers() == Modifier.PUBLIC)) {
				continue; // jump past
			}
			System.out.println("\tField Name: " + field.getName());
			System.out.println("\tField Type: " + field.getType());
			System.out.println("\tIs field primative? " + field.getType().isPrimitive());
			
			System.out.println("\tModifiers bit value: " + Integer.toBinaryString(field.getModifiers()));
			System.out.println("\tdeclared annotations: " + Arrays.toString(field.getAnnotations()));

			System.out.println();

		}
		
		return fields;
	}
	

///// PREVIOUS METHODS /////////////////////////////////////////////////////////////	
	public Annotation[] listClassAnnotations(Class<?> clazz) { // takes in mystery class, renames it clazz
		
		return clazz.getAnnotations();
	}
	
	public void listClassAnnotationsSysout(Class<?> clazz) { // takes in mystery class, renames it clazz

		System.out.println("Printing annotations for the class: " + clazz.getName()); // finds the class and
		

		// prints it
		for(Annotation annotation: clazz.getAnnotations()) {
			System.out.println("annotation is: " + annotation);
		}
		
		System.out.println();
	}

	public static void listPublicConstructors(Class<?> clazz) { // takes in mystery class, renames it clazz

		System.out.println("Printing the public constructors of the class " + clazz.getName()); // finds the class and
																								// prints it

		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("\tName " + constructor.getName());
			System.out.println("\tParam types " + Arrays.toString(constructor.getParameterTypes()) + "\n");
			System.out.println(); // blank space
		}
	}

	
//	
	
	public static void listNonPublicFields(Class<?> clazz) {
		System.out.println("Priting non public fields of class " + clazz.getName());

		Field[] fields = clazz.getDeclaredFields(); // comes from lava.lang.reflext.Field

		if (fields.length == 0) {
			System.out.println("\nThere are no non public fields in " + clazz.getName());
		}

		for (Field field : fields) {
			if ((field.getModifiers() == Modifier.PUBLIC)) {
				continue; // jump past
			}
			System.out.println("\tField Name: " + field.getName());
			System.out.println("\tField Type: " + field.getType());
			System.out.println("\tIs field primative? " + field.getType().isPrimitive());
			
			System.out.println("\tModifiers bit value: " + Integer.toBinaryString(field.getModifiers()));
			System.out.println("\tdeclared annotations: " + Arrays.toString(field.getAnnotations()));

			System.out.println();

		}
	}

	public static void listPublicMethods(Class<?> clazz) {
		System.out.println("Printing public methods of class " + clazz.getName());
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {

			// don't want to see toString/getters setters/etc
			// exlude from the ojbect class
			if (method.getDeclaringClass() == Object.class) {
				continue;
			}
			if ((method.getModifiers() == Modifier.PUBLIC)) {
				continue; // jump past
			}
			System.out.println("\t method name " + method.getName());
			System.out.println("\t parameters passed through " + method.getParameterCount());
			System.out.println("\t get declared class" + method.getDeclaringClass());
			System.out.println("\t method declared annotations: " + Arrays.toString(method.getDeclaredAnnotations()));

			Parameter[] params = method.getParameters();
			for (Parameter param : params) {
				System.out.println("\n\tParameter name: " + param.getName());
				System.out.println("\n\tParameter type: " + param.getType());
			}
		}
		System.out.println();

	}

	
}
