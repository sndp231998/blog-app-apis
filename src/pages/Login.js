import { useState } from "react";
import { toast } from "react-toastify";
import { doLogin } from "../auth";
import { 
  Label,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Row,
  Button,
} from "reactstrap";
import Base from "../components/Base";
import { loginUser } from "../Services/user-service";

const Login = () => {
  const [loginDetail, setLoginDetail] = useState({
    username: "",
    password: "",
  });

  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualValue,
    });
  };

  const handleReset = () => {
    setLoginDetail({
      username: "",
      password: "",
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);
    //------------validation----------------
    if (loginDetail.username.trim() === '' || loginDetail.password.trim() === '') {
      toast.error("Username or password is required !!");
      return;
    }

    //submit the data to server to generate token
    loginUser(loginDetail).then((data) => {
        console.log(data);
       
//save the data to localstorage
doLogin(data,()=>{
    console.log("login detail is saved to localstorage")
    //redirect to user dashboard
    

})

        toast.success("Login success");
      })
      .catch(error => {
        console.log(error);
        if (error.response && (error.response.status === 400 || error.response.status === 404)) {
          toast.error(error.response.data.message);
        } else {
          toast.error("something went wrong on server !! ");
        }
      });
  };

  return (
    <Base>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card>
              <CardHeader>
                <h3>Login Here !!</h3>
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleFormSubmit}>
                  <FormGroup>
                    <Label for="email" >Enter Email</Label>
                    <Input type="text" id="email" placeholder="userid"
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, "username")}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label for="password" >Enter Password</Label>
                    <Input type="password" id="password" placeholder="password"
                      value={loginDetail.password}
                      onChange={(e) => handleChange(e, "password")}
                    />
                  </FormGroup>

                  <Container className="text-center">
                    <Button color="primary" outline>Login</Button>
                    <Button onClick={handleReset} className="ms-2" outline color="secondary">Reset</Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Login;