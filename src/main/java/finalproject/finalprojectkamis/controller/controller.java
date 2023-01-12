/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.finalprojectkamis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalproject.finalprojectkamis.model.entity.Uas;
import finalproject.finalprojectkamis.model.jpa.UasJpaController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Reza Ramadhani
 */
@RestController
@CrossOrigin
@RequestMapping("finalproject")
public class controller {
    //memanggil entity 
    Uas pws = new Uas();
    //memanggil jpa
    UasJpaController ctrl = new UasJpaController();
    
     @GetMapping
    public List<Uas> getData(){
        List<Uas> data = new ArrayList<>(); //menambahkan object pada ArrayList
        try{
        data = ctrl.findUasEntities();
                }catch (Exception e){
                    
                }
                return data;
        
    }  
    
    //get mapping berdasarkan id
    @GetMapping("/{id}")
    public List<Uas> getUasEntities(@PathVariable("id")int id){
    List<Uas> data = new ArrayList<Uas>();
    try{
        pws = ctrl.findUas(id);
        data.add(pws);}
        catch(Exception e){}
        return data;
    }
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "berhasil";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        pws = map.readValue(json_receive, Uas.class);
        ctrl.create(pws);} 
        catch (Exception ex) {
        message = "gagal memasukkan data";
        }
        return message;
               
    }
    
    //put mapping
    @PutMapping
    public String editData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "berhasil";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        pws = map.readValue(json_receive, Uas.class);
        ctrl.edit(pws);} 
        catch (Exception ex) {
        message = "gagal mengubah data";
        }
        return message;
               
    }
    
    //delete
    @DeleteMapping
     public String deleteData(HttpEntity<String> requestdata) throws JsonProcessingException{
        String message = "berhasil";
        try{
        String json_receive = requestdata.getBody();
        ObjectMapper map = new ObjectMapper();
        pws = map.readValue(json_receive, Uas.class);
        ctrl.destroy(pws.getId());} 
        catch (Exception ex) {
        message = "gagal menghapus data";
        }
        return message;
               
    }
    
}

