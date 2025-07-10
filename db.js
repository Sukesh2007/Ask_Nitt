import mongoose from "mongoose";

export const connectDb = async() =>{
    const mongoUri = 'mongodb+srv://sukesh:sukesh123@cluster0.1jsi6ll.mongodb.net/asknitt'
    await mongoose.connect(mongoUri).then( () => {
    console.log('Database connected')
})
}

