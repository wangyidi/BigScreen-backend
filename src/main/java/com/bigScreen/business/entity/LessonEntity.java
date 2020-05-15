package com.bigScreen.business.entity;

import java.io.Serializable;

public class LessonEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5987187131876230672L;

	
	
	private String class_id;
	
	private String lesson;

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	@Override
	public String toString() {
		return "Lesson [class_id=" + class_id + ", lesson=" + lesson + "]";
	}
	
	
	
}
