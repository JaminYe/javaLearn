package cn.jaminye;

import java.io.Serializable;

/**
 * @author Jamin
 * @date 2021/1/19 21:52
 */
public class Person implements Serializable {
	/**
	 * id
	 */
	private String id;
	/**
	 * name
	 */
	private String name;
	/**
	 * age
	 */
	private String age;

	public Person() {
	}

	public Person(String id, String name, String age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}
