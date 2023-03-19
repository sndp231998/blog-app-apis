import {useEffect,useState } from "react";

//server ko link 
import { signUp} from "../Services/user-service";
import {toast} from 'react-toastify';

import { 
    Button,
    Card,
    CardBody,
    CardHeader,
    Col,
    Container,
    Form,
    FormFeedback,
    FormGroup,
    Input,
    Label,
    Row
} from "reactstrap";
import Base from "../components/Base";
const Signup=()=>{
    const[data,setData]=useState({
        name:'',
        email:'',
        password:'',
        about:'',
        
    })
    const[error,setError]=useState({
        errors:{},
        isError:false,
    })

//     useEffect(()=>{
// console.log(data);
//     },[data])

    //----------------handle change----------------
    const handleChange=(event,property)=>{
        //aba yo dynamic vayo(DYnamicly value set vayo)
       setData({...data,[property]:event.target.value})
       

    }
//Reseting the form
const resetData=()=>{
    setData({
        name:'',
        email:'',
        password:'',
        about:'',
    })
}
// --------------submitting the form-------------------------
const submitForm=(event)=>{
    event.preventDefault()
    //console.log(data);
    //data validadte

// if(error.isError){
//     toast.error("Form data is invalid,correct all details then submit");
//     setError({...error,isError:false})
//     return;
// }


//-------call server api for sending data-------------
signUp(data).then((resp)=>{
    console.log(resp);
    console.log("sucess log");
    toast.success("User is reg sucessfully!user id"+resp.id);
    setData({
        name:"",
        email:"",
        password:"",
        about:""
    });

})




.catch((error)=>{
    console.log(error)
    console.log("Error log");
    //handle errors in proper way
    setError({
        errors:error,
        isError:true
    })
});
    
};

    //---------------------------------------------------
    return (
        <Base>
        <Container>

            <Row className="mt-4">
    
    {/* {JSON.stringify(data)} */}

                <Col sm={{size:6,offset:3}}>

                <Card color="" outline="">
    <CardHeader>
      <h3> Fill Information To Register!</h3>
    </CardHeader>
{/*---------------- Creating form ----------------*/}
<CardBody>
<Form onSubmit={submitForm}>
    {/* -----------Name_Field----------- */}
    <FormGroup>    
        <Label for="name">Enter Name</Label>
        <Input type="text" 
        placeholder="Enter here" 
        id="name" 
        onChange={(e)=>handleChange(e,"name")}
        value={data.name}
        invalid={error.errors?.response?.data?.name?true:false}
        />
       <FormFeedback>
        {error.errors?.response?.data?.name}
        </FormFeedback> 
</FormGroup>
{/* ------------Email field -------------------*/}
<FormGroup>
        <Label for="">Enter Email</Label>
        <Input type="email"
         placeholder="Enter Email"
          id="email"
          onChange={(e)=>handleChange(e,'email')}
          value={data.email}
          invalid={error.errors?.response?.data?.email?true:false}
          />
<FormFeedback>
        {error.errors?.response?.data?.email}
        </FormFeedback> 
</FormGroup>
{/* ----------Password field------------------------ */}
       <FormGroup>
         <Label for="password">Enter Password</Label>
        <Input type="password" 
        placeholder="Enter password" id="password"
        onChange={(e)=>handleChange(e,'password')}
        value={data.password}
        invalid={error.errors?.response?.data?.password?true:false}
       />
       <FormFeedback>
        {error.errors?.response?.data?.password}
       </FormFeedback>
      </FormGroup>

      {/*-------------------- About field ------------*/}
      <FormGroup>
        <Label for="about">About</Label>
        <Input type="textarea"
         placeholder="Enter here" id="about" 
         onChange={(e)=>handleChange(e,'about')}
         value={data.about}
         invalid={error.errors?.response?.data?.about?true:false}
         style={{height:"200px"}}/>
          <FormFeedback>
        {error.errors?.response?.data?.about}
       </FormFeedback>
    </FormGroup>
    {/* -------------------Button--------------------- */}
    <Container  className="text-center">
        <Button color="primary" outline>
        Register
        </Button>

        <Button onClick={resetData}color="secondary" type="reset" outline className="ms-2">
        Reset
        </Button>
    </Container>
    {/* ----------------End -----------------*/}
</Form>
</CardBody>

</Card>


                </Col>
            </Row>




            </Container>
        </Base>
    );
    };
export default Signup