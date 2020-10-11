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
	private String password;
	private String passwordConfirm;
	private String loginId;
	private int active;
	private String status;
	
	
	public UserModel(int id, String nama, int nim, String tgl,String loginId) {
		
		this.id = id;
		this.nama = nama;
		this.nim = nim;
		this.tgl = tgl;
		this.loginId = loginId;
	}


	public UserModel(int id, String tgl, String password) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.password = password;
	}
	
	
	
	}




