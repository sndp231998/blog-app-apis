package com.sandip.blog.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="title")
	private String categoryTitle;
	@Column(name="description")
	private String categoryDescription;
	
	//aauta category vitra multiple post haru hunax so,,(one to many
	//cascadeType.All=(if parent delete than child auto delete
	//Cascade=CascadeType.All,ferch=FetchType,LAZY(parent delete vo vane pn childe rNXA
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<Post>posts=new ArrayList<>();
}
