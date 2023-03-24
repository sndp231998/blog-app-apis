
import { myAxios, privateAxios } from "./helper";
//create post function
export const createPost=(postData)=>{
  return privateAxios
  .post(`/user/${postData.userId}/category/${postData.categoryId}/posts`,postData)

  .then(Response=>Response.data);

  };


// import { myAxios } from "./helper";
// import { privateAxios } from "./helper";


// //create post function
// export const createPost=(postData)=>{
//    // console.log(postData)
//    return privateAxios
// //http://localhost:9090/api/v1/user/15/category/2/posts
// .post('/user/${postData.userId}/category/${postData.categoryId}/posts',postData)
// .then(response=>response.data)
// };
