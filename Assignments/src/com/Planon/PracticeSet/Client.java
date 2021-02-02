package com.Planon.PracticeSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Client {

	public static void main(String[] args) {
	
		List<Student> studentList = new ArrayList<>();
		List<String> subjectlist = new ArrayList<>();
		List<String> distinctAllSubjects = new ArrayList<>();
		subjectlist.add("Java");
		subjectlist.add("Maths");
		subjectlist.add("Physics");
		subjectlist.add("Chemistry");
		subjectlist.add("English");
		studentList.add(new Student(123, "Aastha", subjectlist, "Science"));
		subjectlist = new ArrayList<>();
		subjectlist.add("Java");
		subjectlist.add("Hindi");
		subjectlist.add("C++");
		subjectlist.add("Social Science");
		subjectlist.add("English");
		studentList.add(new Student(122, "Aastha Rai", subjectlist, "Science1"));
		
		distinctAllSubjects = studentList.stream()
				.map(oneStudent -> oneStudent.getSubjects())
				.flatMap(subjectsList -> subjectsList.stream())
				.distinct()
				.collect(Collectors.toList());
		System.out.println(distinctAllSubjects);
		

	}

	private static void getDistinctSubjects(Student onestudent) {
		
	}

}
