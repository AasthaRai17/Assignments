package com.Planon.PracticeSet;

import java.util.List;

public class Student {
	public long id;
	public String name;
	public String program;
	public List<String> subjects;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getProgram() {
		return program;
	}
	public List<String> getSubjects() {
		return subjects;
	}
	
	public Student(long id, String name, List<String> subjects, String program) {
		this.id=id;
		this.name=name;
		this.subjects=subjects;
		this.program=program;
	}
} 
