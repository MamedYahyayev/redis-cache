package az.maqa.redis.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Friend implements Serializable{
	
	private int id;
	private String name;
	private int age;
}
