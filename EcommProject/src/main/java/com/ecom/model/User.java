package com.ecom.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

//theses lombok annotations help us to create getters and setters
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    
    
	@Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userIdGenerator")
    @SequenceGenerator(name = "userIdGenerator",sequenceName = "user_id_generator_seq")
    private long  userId;
	
	@NotNull(message="username should not be null")
    @Column(name = "userName")
    private String userName;
	
	@NotNull(message="Password should not be null")
    @Column(name = "password")
    private String password;
	
	@NotNull(message="Role should not be null")
    @Column(name = "role")
    private String role;

	@NotNull(message="username should not be null")
    @Column(name = "isActive")
    private long isActive;
    
    @Column(name="token")
    String jwtToken;
}

