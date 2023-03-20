import React from 'react'
import AddPost from '../../components/AddPost'
//import AddPost from '../../components/AddPost'
import Base from '../../components/Base'
import { Container } from 'reactstrap'
//import NewFeed from '../../components/NewFeed'
// import { useState } from 'react'
// import { useEffect } from 'react'
// import { getCurrentUserDetail } from '../../auth'
// import { deletePostService, loadPostUserWise } from '../../services/post-service'
// import { toast } from 'react-toastify'
// import Post from '../../components/Post'
const Userdashboard=() =>{
  return (
  <Base>
  

  <Container>
  <AddPost/>
  </Container>
  </Base>
  )
}

export default Userdashboard