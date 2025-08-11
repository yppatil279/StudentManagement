
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static int intValue(String s){
        return Integer.parseInt(s);
    }

    private static String calculateGrade(int total) {
        if(total > 255) return "A";
        else if(total > 210) return "B";
        else if(total > 165) return "C";
        else if(total > 120) return "D";
        else if(total > 75) return "E";
        else return "F";
    }

    public static void addStudent(Connection connection){
        String query = "INSERT INTO marksheet(roll_no, name, english, maths, science, total, grade) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            System.out.print("Enter roll no: ");
            String one = sc.nextLine();
            preparedStatement.setInt(1, Integer.parseInt(one));

            System.out.print("Enter name: ");
            String two = sc.nextLine();
            preparedStatement.setString(2, two);

            System.out.print("Enter english marks: ");
            String three = sc.nextLine();
            preparedStatement.setInt(3, Integer.parseInt(three));

            System.out.print("Enter maths marks: ");
            String four = sc.nextLine();
            preparedStatement.setInt(4, Integer.parseInt(four));

            System.out.print("Enter science marks: ");
            String five = sc.nextLine();
            preparedStatement.setInt(5, Integer.parseInt(five));

            int total = Integer.parseInt(three) + Integer.parseInt(four) + Integer.parseInt(five);
            preparedStatement.setInt(6, total);

            preparedStatement.setString(7, calculateGrade(total));


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("New student's data added");
            } else {
                System.out.println("not able to add new data");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewStudent(Connection connection){
        String query = "SELECT * FROM marksheet";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Roll no: " + resultSet.getInt("roll_no"));
                System.out.println("Name: "  + resultSet.getString("name"));
                System.out.println("English marks: " + resultSet.getInt("english"));
                System.out.println("Maths marks: " + resultSet.getInt("maths"));
                System.out.println("Science marks: " + resultSet.getInt("science"));
                System.out.println("Total Marks: " + resultSet.getInt("total"));
                System.out.println("Grade: " + resultSet.getString("grade"));
                System.out.println("--------------------------------------------------");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void viewOneStudent(Connection connection){
        try{
            String query = "SELECT * FROM marksheet WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter roll number to get student's data: ");
            String ask = sc.nextLine();
            preparedStatement.setInt(1, Integer.parseInt(ask));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println("Roll no: " + resultSet.getInt("roll_no"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("English marks: " + resultSet.getInt("english"));
                System.out.println("Maths marks: " + resultSet.getInt("maths"));
                System.out.println("Science marks: " + resultSet.getInt("science"));
                System.out.println("Total Marks: " + resultSet.getInt("total"));
                System.out.println("Grade: " + resultSet.getString("grade"));
                System.out.println("--------------------------------------------------");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void delStudent(Connection connection){
        try{
            String query = "DELETE FROM marksheet WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter roll number to delete student: ");
            String delData = sc.nextLine();
            preparedStatement.setInt(1, Integer.parseInt(delData));
            int rowsAffected2 = preparedStatement.executeUpdate();
            if(rowsAffected2 > 0){
                System.out.println("Student's data deleted");
            } else {
                System.out.println("not able to delete data");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static Scanner sc = new Scanner(System.in);
    private static final String url = "jdbc:mysql://localhost:3306/project1";
    private static final String username = "root";
    private static final String password = "Admin@279";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, username, password);

            boolean loop = true;
            while(loop){
                System.out.println("\n==== Student Result Management System ====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by Roll No");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                String c = sc.nextLine();
                int choice = Integer.parseInt(c);

                switch(choice){
                    case 1:
                        addStudent(connection);break;

                    case 2:
                        viewStudent(connection);break;

                    case 3:
                        viewOneStudent(connection);break;

                    case 4:
                        delStudent(connection);break;

                    case 5:
                        System.out.println("bye");
                        loop = false;
                        break;

                    default:
                        System.out.println("invalid input");
                }
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}