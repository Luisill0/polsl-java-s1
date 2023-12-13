/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package polsl.pl.model;

import java.lang.annotation.Documented;

/**
 * Annotate route a Servlet can redirect to 
 * 
 * @author luiz
 * @version 1/0
 */
public @Documented @interface Redirects { String to(); }
