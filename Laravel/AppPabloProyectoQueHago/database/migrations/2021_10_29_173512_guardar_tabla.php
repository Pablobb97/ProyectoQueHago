<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class GuardarTabla extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('guardar', function (Blueprint $table) {
            $table->id();
            $table->string('tituloManana');
            $table->string('descriptionManana');
            $table->string('fechaManana');
            $table->string('localizacionManana');
            $table->string('precioManana');
            $table->string('nombreComida');
            $table->string('localizacionComida');
            $table->string('precioComida');
            $table->string('distritoComida');
            $table->string('productosComida');
            $table->string('puntuacionComida');
            $table->string('tituloTarde');
            $table->string('descriptionTarde');
            $table->string('fechaTarde');
            $table->string('localizacionTarde');
            $table->string('precioTarde');
            $table->string('nombreCena');
            $table->string('localizacionCena');
            $table->string('precioCena');
            $table->string('distritoCena');
            $table->string('productosCena');
            $table->string('puntuacionCena');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('guardar');
    }
}
