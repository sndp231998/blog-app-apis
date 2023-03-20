
import { useEffect ,useState,useRef} from "react"
import { Card, CardBody, Form, Input, Label, Button, Container} from "reactstrap"
import { loadAllCategories } from "../Services/category-service"
import JoditEditor from "jodit-react"
//import { createPost as doCreatePost, uploadPostImage } from "../services/post-service"
import { getCurrentUserDetail } from "../auth"
//import { toast } from "react-toastify"
import { createPost as doCreatePost } from "../Services/post-service"

const   AddPost  = ()=> {

const editor=useRef(null)
//const [content,setContent] =useState('')

const [categories,setCategories]=useState([])
const [user,setUser]=useState(undefined)

const[post,setPost]=useState({
    title:'',
    content:'',
    categoryId:-1
})

    useEffect(
        ()=>{
setUser(getCurrentUserDetail())
loadAllCategories().then((data)=>{
   // console.log(data)
    setCategories(data)
}).catch(error=>{
    console.log(error)
})
        },
        []
    )
    //fieldChange function
    const fieldChanged=(event)=>{
       // console.log(event)
        setPost({...post,[event.target.name]:event.target.value})
    }
    const contentFieldChanged=(data)=>{
        setPost({...post,'content':data})
    }
    //create post function
    const createPost=(event)=>{
        event.preventDefault();
      //  console.log(post)
        if(post.title.trim()===''){
            alert("Post title is required")
            return;
        }
        if(post.content.trim()===''){
            alert("post content is required !!")
            return ;
        }if(post.categoryId===''){
           alert("select  category !!") 
           return;
        }
        //submit the form on server
        post['userId']=user.id
doCreatePost(post).then(data=>{
    alert("Post created")
    console.log(post)
}).catch((error)=>{
    alert("error")
    console.log(error)
})
    }
    return (
        <div className="wrapper">
        <Card className="shadow-sm  border-0 mt-2">
            <CardBody>
                 {/* {JSON.stringify(post)}  */}
                <h3>What going in your mind ?</h3>
                <Form onSubmit={createPost}>
                    <div className="my-3">
                        <Label for="title" >Post title</Label>
                        <Input
                            type="text"
                            id="title"
                            placeholder="Enter here"
                            className="rounded-0"
                            name="title"
                            onChange={fieldChanged}
                        />
                    </div>
            {/* ------------------------------------         */}
            <div className="my-3">
                        <Label for="title" >Post Content</Label>
                        {/* <Input
                            type="textarea"
                            id="content"
                            placeholder="Enter here"
                            className="rounded-0"
                            name="content"
                            //onChange={fieldChanged}
                        /> */}
                        <JoditEditor ref={editor}
                        value={post.content}
                        onChange={contentFieldChanged}   />
                    </div>
            <div className="my-3">
                        <Label for="category" >Post Category</Label>
                        <Input
                            type="select"
                            id="category"
                            placeholder="Enter here"
                            className="rounded-0"
                            name="categoryId"
                            onChange={fieldChanged}
                            defaultValue={0}
                        >
                            <option disabled value={0}>--selected Category--</option>
                            {
                                categories.map((category)=>(
                                    <option  value={category.categoryId} key={category.categoryId}>
                                        {category.categoryTitle}
                                    </option>
                                ))
                            }

                    </Input>
                    </div>

                    <Container>
                        <Button type="submit" className="rouned-0" color="primary">
                            Create Post
                        </Button>
                        <Button type="" className="rouned-0 ms-2" color="danger">
                            Resest Content
                        </Button>
                    </Container>

        </Form>
      
    </CardBody>
 </Card>
        </div>
    )
}
export default AddPost