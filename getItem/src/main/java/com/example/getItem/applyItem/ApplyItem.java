package com.example.getItem.applyItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ApplyItem {
		
	private long id;
	private String memo;
	private String hostName;
	private String bidderName;
	private String cntHave;
	private String cntWant;
	private long createdTime;
	private long crcHave;
	private long crcWant;
	private String status;

}
