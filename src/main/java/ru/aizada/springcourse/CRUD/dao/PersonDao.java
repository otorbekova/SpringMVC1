package ru.aizada.springcourse.CRUD.dao;


import org.springframework.stereotype.Component;
import ru.aizada.springcourse.CRUD.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/first_DB";
    private static final String UserName = "postgres";
    private static final String Password = "240901";
    private static Connection connection;
    private int count = 0;

    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, UserName, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT*FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL); // ничего не изменяет

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        //  return people1.stream().filter(person->person.getId()==id).findAny().orElse(null);
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));
            statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    //добавляет в БД
    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people1.add(person);
      /*  try {
            Statement statement= connection.createStatement();
            String sql="INSERT INTO PERSON VALUES("+1+",'"+person.getName()+"',"+person.getAge()+
                    ",'"+person.getEmail()+"')";
            statement.executeUpdate(sql); //обновляет
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO  PERSON values (1,?,?,?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int id, Person person) {
//        Person updP=show(id);
//        updP.setName(person.getName());
//        updP.setAge(person.getAge());
//        updP.setEmail(person.getEmail());
        try {
            PreparedStatement statement=connection.prepareStatement("update Person set name=?,age=?,email=? where id=?");
        statement.setString(1,person.getName());
        statement.setInt(2,person.getAge());
        statement.setString(3, person.getEmail());
            statement.setInt(4,id);
        statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void delete(int id) {
        //   people1.removeIf(p->p.getId()==id); //Лямдовые выражение
        try {
            PreparedStatement statement=connection.prepareStatement("delete from PERSON where id=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




