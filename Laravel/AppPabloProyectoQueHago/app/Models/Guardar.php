<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Guardar extends Model
{
    protected $fillable = [
        'tituloManana',
        'descriptionManana',
        'fechaManana',
        'localizacionManana',
        'precioManana',
        'nombreComida',
        'localizacionComida',
        'precioComida',
        'distritoComida',
        'productosComida',
        'puntuacionComida',
        'tituloTarde',
        'descriptionTarde',
        'fechaTarde',
        'localizacionTarde',
        'precioTarde',
        'nombreCena',
        'localizacionCena',
        'precioCena',
        'distritoCena',
        'productosCena',
        'puntuacionCena',
    ];
}
