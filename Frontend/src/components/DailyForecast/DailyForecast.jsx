function DailyForecast(props){
    console.log(props)
    return(
        <tr>
            <td>{props.city}</td>
            <td>{props.date}</td>
            {props.maxTemp !== null && props.maxTemp !== undefined &&(
                <td>{props.maxTemp}</td>
            )}
            <td>{props.desc}</td>
        </tr>

    )

}

export default DailyForecast;