package com.git.manager.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long groupId;
	@Column(columnDefinition = "VARCHAR(1000)")
	private String memo;
	
	// BLOB: binary large object
	@Column(columnDefinition = "TEXT")
	private String title;
	private long start;
	


}
