package com.tesdb.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel{
	private int id;

	private String nama;
	private int nim;
	private String tgl;
	
	}




