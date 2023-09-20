import WriterApi from "../../api/WriterApi";


function WriterDashboard (){
    WriterApi.getLoggedInWriter().then(function(data){
        console.log(data);
    });
    return(
        <div></div>
    );
}
export default WriterDashboard;