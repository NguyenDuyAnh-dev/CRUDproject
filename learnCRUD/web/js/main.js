/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// delete account
function doDelete(id){
    if (confirm("are you sure to delete this: " + id)){
        window.location = "delete?id=" + id;
    }
}
