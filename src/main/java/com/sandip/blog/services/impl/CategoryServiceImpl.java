package com.sandip.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandip.blog.Exceptions.ResourceNotFoundException;
import com.sandip.blog.entities.Category;

import com.sandip.blog.payloads.CategoryDto;
import com.sandip.blog.repositories.CategoryRepo;
import com.sandip.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper; 
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}
	
	//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}
	
	//--------------------------//delete--------------------------//
	
//	public void deleteUser(Integer userId) {
//		 User user=this.userRepo.findById(userId)
//				 .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
//	     this.userRepo.delete(user);
//		}
	@Override
	public void deleteCategory(Integer categoryId) {
		 Category cat=this.categoryRepo.findById(categoryId)
				 .orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
	     this.categoryRepo.delete(cat);	
	}
	
	//----------------get particular categoryId--------------------------------
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category id",categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}
	
	//----------------getAll categories-----------------------------------//
	@Override
	public List<CategoryDto> getCategories() {
		List<Category>categories=this.categoryRepo.findAll();
		List<CategoryDto>catDto=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return catDto;
	}

}
