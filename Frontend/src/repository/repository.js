import axios from "../custom-axios/axios";

const WeatherService = {
    fetchForecasts:() => {
        return axios.get(`/load`)
    },

    getHotDays:()=>{
      return axios.get(`/hotDays`)
    },

    getRainyDays:()=>{
        return axios.get(`/rainyDays`)
    }
}

export default WeatherService;