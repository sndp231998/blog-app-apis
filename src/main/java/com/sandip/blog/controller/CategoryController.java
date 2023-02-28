package com.sandip.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandip.blog.payloads.ApiResponce;
import com.sandip.blog.payloads.CategoryDto;

import com.sandip.blog.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto cateogDto){
	
		CategoryDto createCategory=this.categoryService.createCategory(cateogDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
//	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
//		UserDto updatedUser=this.userService.updateUser(userDto,uid);
//		return ResponseEntity.ok(updatedUser);
//		
//		}
	
	//update
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId){
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, catId);
	return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
//	@PutMapping("/{catId}")
//	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer catid){
//	CategoryDto updatedCategoryed=this.categoryService.updateCategory(categoryDto,catid);
//	return ResponseEntity<categoryDto>(updatedCategoryed,HttpStatus.OK);
//	//return ResponseEntity.ok(updatedCategoryed);
//	
//	}
//	
//	@PutMapping("/{catId}")
//	public ResponseEntity<CategoryDto>updateCategory(@RequestBody CategoryDto categoryDto, 
//			@PathVariable Integer catId){
//	
//		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,catId);
//		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
//		
//	}
	
	//delete
//	@DeleteMapping("/{catId}")
//	public ResponseEntity<ApiResponce>deleteCategory(@PathVariable Integer catid){
//		this.categoryService.deleteCategory(catid);
//		return  new ResponseEntity<ApiResponce>(new ApiResponce("Category deleted SucessFully", true),HttpStatus.OK);
//	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("category is deleted successfully !!", true),
				HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer catId){
	   CategoryDto  categoryDto=this.categoryService.getCategory(catId);
	   return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getAllCategories(){
		//return ResponseEntity.ok(this.categoryService.getAllCategory());
		List<CategoryDto>categories=this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
}
