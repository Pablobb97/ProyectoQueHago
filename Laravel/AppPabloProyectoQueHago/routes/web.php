<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('info/{nombreyapellido}',
    [App\Http\Controllers\PeticionController::class,'devolverJsonUsuario']);

Route::get('registro/{nombreyapellido}/{nombreusuario}/{contrasena}/{email}/{fechanac}',
    [App\Http\Controllers\PeticionController::class,'registrarUsuario']);

Route::get('guardar/{tituloManana}/{descriptionManana}/{fechaManana}/{localizacionManana}/{precioManana}/{nombreComida}/{localizacionComida}/{precioComida}/{distritoComida}/{productosComida}/{puntuacionComida}/{tituloTarde}/{descriptionTarde}/{fechaTarde}/{localizacionTarde}/{precioTarde}/{nombreCena}/{localizacionCena}/{precioCena}/{distritoCena}/{productosCena}/{puntuacionCena}',
    [App\Http\Controllers\PeticionController::class,'guardar']);

Route::get('login/{nombreusuario}/{contrasena}',
    [App\Http\Controllers\PeticionController::class,'logearUsuario']);

Route::get('comprobar/{nombreusuario}',
    [App\Http\Controllers\PeticionController::class,'comprobarUsuario']);

Route::get('/todo',
    [App\Http\Controllers\PeticionController::class,'devolverTodo']);
