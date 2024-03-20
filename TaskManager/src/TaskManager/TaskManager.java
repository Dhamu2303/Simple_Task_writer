package TaskManager;
import java.io.*;
import java.util.*;

public class TaskManager {
	private static Scanner scanner;
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		ArrayList<String> tasks=readTasksFromFile();
		while(true) {
			System.out.println("\nTask Manager Menu: ");
			System.out.println("1. Add Tasks");
			System.out.println("2. View Tasks");
			System.out.println("3. Mark Task as Completed");
			System.out.println("4. Delete Task");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			int choices=scanner.nextInt();
			scanner.nextLine();
			switch(choices) {
			case 1:
				System.out.print("Enter task description: ");
				String newTask=scanner.nextLine();
				tasks.add(newTask);
				writeTasksToFile(tasks);
				System.out.println("Tasks added successfully");
				break;
			case 2:
				try{if(tasks.isEmpty()) {
					System.out.println("No tasks available.");
				}else {
					System.out.println("Tasks:");
					for(int i=0;i<=tasks.size()-1;i++) {
						System.out.println((i+1)+"."+tasks.get(i));
					}
				}
				}catch(Exception e) {
					System.out.println("Error found "+e.getMessage());
				}
				break;
			case 3:
				System.out.print("Enter the Task number to mark as completed: ");
				int tasksIndex=scanner.nextInt();
				scanner.nextLine();
				if(tasksIndex>=1&&tasksIndex<=tasks.size()) {
					tasks.remove(tasksIndex-1);
					writeTasksToFile(tasks);
					System.out.println("Tasks marked as completed.");
				}else {
					System.out.println("Invalid Tasks number.");
				}
				break;
			case 4:
				System.out.print("Enter the task number to delete: ");
				int deleteIndex=scanner.nextInt();
				scanner.nextLine();
				if(deleteIndex>=1&&deleteIndex<=tasks.size()) {
					tasks.remove(deleteIndex-1);
					writeTasksToFile(tasks);
					System.out.println("Task deleted successfully.");
				}else {
					System.out.println("Invalid Task number.");
				}
				break;
			case 5:
				System.out.println("Exiting...");
				System.exit(0);
				default:
					System.out.println("Invalid choice.Please enter a number between 1 and 5.");
			}
		}
	}
	private static ArrayList<String> readTasksFromFile(){
		ArrayList<String> tasks=new ArrayList<>();
		try(BufferedReader reader=new BufferedReader(new FileReader("F:\\tasks.txt"));){
			String line;
			while((line=reader.readLine())!=null){
				tasks.add(line);
				}
		}
			catch (IOException e) {
				System.out.println("Error reading tasks from file: "+e.getMessage());
			}
			return tasks;
		}
		private static void writeTasksToFile(ArrayList<String> tasks) {
			try(BufferedWriter writer=new BufferedWriter(new FileWriter("F:\\tasks.txt"))){
				for(String task:tasks) {
					writer.write(task);
					writer.newLine();
				}
			}catch(IOException e) {
				System.out.println("Error writing tasks to file: "+e.getMessage());
			}
		}
}
