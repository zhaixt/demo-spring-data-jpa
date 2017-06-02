package com.model;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Student implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Student(Long id) {
		this.id = id;
	}

	Student() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Base{" + "id=" + id + ", name='" + name + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Student))
			return false;

		Student base = (Student) o;

		if (id != null ? !id.equals(base.id) : base.id != null)
			return false;
		return name != null ? name.equals(base.name) : base.name == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
