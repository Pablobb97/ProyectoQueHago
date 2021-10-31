<?php

namespace App\Http\Controllers;

use App\Models\Usuario;
use App\Models\Guardar;

class PeticionController extends Controller
{
    function devolverJsonUsuario($nombre){
        $usuario=Usuario::All();
        $esteUsuario="Usuario desconocido";
        foreach($usuario as $user){
            if ($user->nombreusuario==$nombre) {
                $esteUsuario = $user;
            }
        }
        return $esteUsuario;
    }
    
    function registrarUsuario($nombreyapellido,$nombreusuario,$contraseña,$email,$fechanac){
        $user =new Usuario();
        $user->nombreyapellido=$nombreyapellido;
        $user->nombreusuario=$nombreusuario;
        $user->contraseña=$contraseña;
        $user->email=$email;
        $user->fechanac=$fechanac;
        $user->save();
        return "Usuario Registrado";
    }
    
    function guardar($tituloManana,$descriptionManana,$fechaManana,$localizacionManana,$precioManana,$nombreComida,$localizacionComida,$precioComida,$distritoComida,$productosComida,$puntuacionComida,$tituloTarde,$descriptionTarde,$fechaTarde,$localizacionTarde,$precioTarde,$nombreCena,$localizacionCena,$precioCena,$distritoCena,$productosCena,$puntuacionCena){
        $guardar =new Guardar();
        $guardar->tituloManana = $tituloManana;
        $guardar->descriptionManana = $descriptionManana;
        $guardar->fechaManana = $fechaManana;
        $guardar->localizacionManana = $localizacionManana;
        $guardar->precioManana = $precioManana;
        $guardar->nombreComida = $nombreComida;
        $guardar->localizacionComida = $localizacionComida;
        $guardar->precioComida = $precioComida;
        $guardar->distritoComida = $distritoComida;
        $guardar->productosComida = $productosComida;
        $guardar->puntuacionComida = $puntuacionComida;
        $guardar->tituloTarde = $tituloTarde;
        $guardar->descriptionTarde = $descriptionTarde;
        $guardar->fechaTarde = $fechaTarde;
        $guardar->localizacionTarde = $localizacionTarde;
        $guardar->precioTarde = $precioTarde;
        $guardar->nombreCena = $nombreCena;
        $guardar->localizacionCena = $localizacionCena;
        $guardar->precioCena = $precioCena;
        $guardar->distritoCena = $distritoCena;
        $guardar->productosCena = $productosCena;
        $guardar->puntuacionCena = $puntuacionCena;
        $guardar->save();
        return "Guardado Registrado";
    }
    
    function logearUsuario($nombreusuario,$contraseña){
        $usuario=Usuario::All();
        $incorrecto="00";
        $correcto="11";
        $esteUsuario="00";
        $resultado="";
        foreach($usuario as $user){
            if ($user->nombreusuario==$nombreusuario) {
                if ($user->contraseña==$contraseña) {
                    $esteUsuario = $correcto;
                }
            }
        }
        if ($esteUsuario == $correcto) {
            $resultado="11";
        }else{
            $resultado="00";
        }
        return $resultado;
    }
    
    function comprobarUsuario($nombreusuario){
        $usuario=Usuario::All();
        $incorrecto="0";
        $correcto="1";
        $esteUsuario="0";
        foreach($usuario as $user){
            if ($user->nombreusuario==$nombreusuario) {
                $esteUsuario = $correcto;
            }
        }
        if ($esteUsuario == $correcto) {
            return $correcto;
        }else{
            return $incorrecto;
        }
        
    }
    
    function devolverTodo(){
        $usuario=Usuario::All();
        return $usuario;
    }
}
