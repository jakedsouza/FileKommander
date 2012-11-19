/**
 * 
 */
package com.poly.nlp.filekommander.file.actions;

import gate.Annotation;

/**
 * @author jake
 * 
 */
public class FileActionUtils {

	public static void CloseAction() {

	}

	public static void CreateAction() {

	}

	public static void DeleteAction() {

	}

	public static void ExistsAction() {

	}

	public static void InsertAction() {

	}

	public static void OpenAction() {

	}

	public static void RemoveAction() {

	}

	public static void RenameAction() {

	}

	public static void ReplaceAction() {

	}

	public static void StatsAction() {

	}

	public static void callAction(String actionType, Annotation annotation) {
		if(actionType == null )
			return;
		System.out.println(actionType);
		switch (actionType) {
		case "close":
			CloseAction();
			break;
		case "create":
			CreateAction();
			break;
		case "delete":
			DeleteAction();
			break;
		case "exists":
			ExistsAction();
			break;
		case "insert":
			InsertAction();
			break;
		case "open":
			OpenAction();
			break;
		case "remove":
			RemoveAction();
			break;
		case "rename":
			RenameAction();
			break;
		case "replace":
			ReplaceAction();
			break;
		case "stats":
			StatsAction();
			break;
		default:
			break;
		}
		
		
	}

}
