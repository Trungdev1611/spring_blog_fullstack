
import { useEffect } from 'react'
import './App.css'
import { Apiclient } from './apis/config'

function App() {
  useEffect(() => {
   async  function getData() {
      const res = await Apiclient.get("/api/posts")
      console.log("res", res)
    }
    getData()
  }, [])
  return (
      <h1>Helllo ReactJs</h1>
      
  )
}

export default App
