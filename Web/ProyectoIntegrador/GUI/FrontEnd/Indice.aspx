<%@ Page Title="" Language="C#" MasterPageFile="~/GUI/FrontEnd/FrontMetheor.Master" AutoEventWireup="true" CodeBehind="Indice.aspx.cs" Inherits="ProyectoIntegrador.GUI.FrontEnd.Indice" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
        <!-- .navbar.navbar-fixed -->

    <!-- Intro Section -->
    <section class="intro-section" style="background-image: url(../../img/home/intro/bakku01.jpg);" data-stellar-background-ratio="0.65">
      <span class="overlay" style="opacity: .85;"></span>
      <div class="container text-center">
        <div class="intro-logo space-bottom">
          <img src="../../img/home/intro/logoXimbal.png" alt="Metheor">
        </div><!-- .intro-logo -->
        <p class="text-light opacity-50">Conoce el camino.</p>
        <ul class="sharing-links light-skin space-bottom">
          <li><a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="fa fa-twitter"></i>0</a></li>
          <li><a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook"></i>1</a></li>
          <li><a href="#" data-toggle="tooltip" data-placement="top" title="Instagram"><i class="fa fa-instagram"></i>0</a></li>
        </ul><!-- .sharing-links -->
      </div><!-- .container -->

      <!-- Remove class ".layer-parallax" to disable mouse parallax effect on screens. -->
      <div class="intro-phone-wrap layer-parallax">
        <div class="phone layer" data-depth="0.25">
          <img src="../../img/home/intro/phone.png" alt="Phone">
          <div class="screen">
            <img src="../../img/home/intro/screen01.jpg" alt="Screen 1"/>
          </div>
        </div><!-- .phone -->
        <div class="screen-first-right layer" data-depth="0.15">
          <img src="../../img/home/intro/screen02.jpg" alt="Screen 2">
        </div><!-- .screen-first-right -->
        <div class="screen-first-left layer" data-depth="0.15">
          <img src="../../img/home/intro/screen03.jpg" alt="Screen 3">
        </div><!-- .screen-first-left -->
        <div class="screen-second-right layer" data-depth="0.05">
          <img src="../../img/home/intro/screen04.jpg" alt="Screen 4">
        </div><!-- .screen-second-right -->
        <div class="screen-second-left layer" data-depth="0.05">
          <img src="../../img/home/intro/screen05.jpg" alt="Screen 5">
        </div><!-- .screen-second-left -->
      </div><!-- .intro-phone-wrap -->
      <span class="inner-shadow"></span>
    </section><!-- .intro-section -->

    <!-- Market Buttons -->
    <section class="fw-section bg-white padding-top-3x padding-bottom-3x" id="markets">
      <div class="container text-center">
        <h6 class="text-thin"><b>Aplicación disponible en:</b></h6>
          <!--<a href="#" class="app-store-btn"></a>-->
          <a href="#" class="google-play-btn"></a>
          <!--<a href="#" class="windows-btn"></a>-->
          <!--<a href="#" class="amazon-btn"></a>-->
          <!--<div class="market-counter">-->
        <div class="market-counter">
          <i class="pe-7s-download"></i>
          <span>155</span>
          descargas
        </div><!-- .market-counter -->
      </div><!-- .container -->
    </section><!-- .fw-section.bg-white -->

    <div class="page-wrapper">

    <section class="container-fluid">

        <h2 class="block-title text-center"><b>¿Sabías qué...</b></h2>

       <div class="grid isotope-grid col-4">
        <div class="grid-sizer"></div>
        <div class="gutter-sizer"></div>



          <!-- Post -->

           <div class="col-sm-1"></div>

        
        <div class="grid-item">
            
          <article class="tile post-tile format-image">
            <div class="post-thumb">
              <img src="../../img/blog/blog-grid/post01.jpg" alt="Post 1">
              
            </div>
            <div class="tile-body post-body">
              <div class="post-format"></div>
              <h3 class="post-title"><b>La Catedral de Mérida...</b></h3>
              <p><b>... fue la primera catedral levantada en la América continental (tierra firme), y también la más antigua de México? 
                  <br/>
                  Sólo la Catedral de Santo Domingo, en toda la América, es más antigua que la de Yucatán. 
                 </b></p>
            </div>
          </article><!-- .tile.post-tile -->
        </div><!-- .grid-item -->

          
           <div class="col-sm-1"></div>

          <!-- Post -->
        <div class="grid-item">
          <article class="tile post-tile format-image">
            <div class="post-thumb">
              <img src="../../img/blog/blog-grid/post02.jpg" alt="Post 2">
              
            </div>
            <div class="tile-body post-body">
              <div class="post-format"></div>
              <h3 class="post-title"><b>Noches de Leyendas...</b></h3>
              <p><b>... se pueden vivir todos los viernes y sábados en el centro histórico a partir de las 8:30 pm.?<br />
                  Noches de Leyendas es combinación de un tour / una obra de teatro con enfoque histórico y turístico.
                 </b></p>
              
            </div>
          </article><!-- .tile.post-tile -->
        </div><!-- .grid-item -->

               <div class="col-sm-1"></div>



          <!-- Post -->
        <div class="grid-item">
          <article class="tile post-tile format-image">
            <div class="post-thumb">
              <img src="../../img/blog/blog-grid/post03.jpg" alt="Post 3">
              
            </div>
            <div class="tile-body post-body">
              <div class="post-format"></div>
              <h3 class="post-title"><b>La Calesa...</b></h3>
              <p><b>... se trata de un carruaje jalado por un caballo, las cuales han estado en uso desde la época colonial, antes como transporte y ahora como atractivo turístico?
                  <br />
                  <br />
                  <br />
                 </b></p>
              
            </div>
          </article><!-- .tile.post-tile -->
        </div><!-- .grid-item -->


            </div><!-- .grid.isotope-grid.col-3 -->
        </section>



        <section class="fw-section parallax text-center padding-top-3x padding-bottom-3x" style="background-image: url(../../img/home/video-bg.jpg);" data-stellar-background-ratio="0.65">
      <span class="overlay" style="opacity: .78;"></span>
      <div class="container padding-top-3x padding-bottom-3x">
        <!-- Remove ".light-skin" class to alter appearance. -->

          <div class="row">
          <div class="col-lg-5 col-lg-offset-1 col-sm-6 padding-bottom-3x mobile-center">
            <h2 class="text-light"><b>Nuestra Misión</b></h2>
            <div class="video-popup-placeholder">
              <img src="../../img/home/intro/video-popup.jpg" alt="Video">
            </div><!-- .video-popup-placeholder -->
            <p class="text-light opacity-50 hidden-xs" style="color:white"<b></b></p>
                <h5>Acompañar al turísta en su aventura a través del centro histórico de la ciudad y brindarle la mejor experiencia cultural, así como dejar huella en el visitante con el fin de promover al mundo nuestra bella ciudad.</h5>
            
          </div><!-- .col-lg-5. col-lg-offset-1 -->

          <div class="col-lg-5 col-lg-offset-1 col-sm-6 padding-bottom-3x mobile-center">
            <h2 class="text-light"><b>Nuestra Visión</b></h2>
            

              <div class="box-with-icon horizontal-box light-skin">
              <div class="box-with-icon-icon">
                  <i class="pe-7s-coffee"></i>
              </div>
              <div class="box-with-icon-content">
                <h3 class="box-with-icon-title">Servicio</h3>
                <p>Ofrecer el mejor servicio de asistentcia turística actual y a futuro.</p>
              </div>
            </div>


              <div class="box-with-icon horizontal-box light-skin">
              <div class="box-with-icon-icon">
                <i class="pe-7s-calculator"></i>
              </div>
              <div class="box-with-icon-content">
                <h3 class="box-with-icon-title">Ingresos</h3>
                <p>Obtener ingresos a través de la implementación de publicidad de negocios locales.</p>
              </div>
            </div>


              <div class="box-with-icon horizontal-box light-skin">
              <div class="box-with-icon-icon">
                <i class="pe-7s-diskette"></i>
              </div>
              <div class="box-with-icon-content">
                <h3 class="box-with-icon-title">Software</h3>
                <p>Colocar la base a futuros proyectos de desarrollo de software turístico.</p>
              </div>
            </div>






            
          </div><!-- .col-lg-5. col-lg-offset-1 -->
        </div><!-- .row -->


        
      </div><!-- .container -->
    </section><!-- .fw-section -->





    <!-- Features -->
    <section class="fw-section bg-white border-top padding-top-3x padding-bottom-3x" id="caracteristicass">
      <div class="container">
        <h2 class="block-title text-center"><b>Características Impresionantes</b>
          <small> </small>
        </h2>
        
        <div class="row">
          <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-icon text-center">
              <div class="box-with-icon-icon">
                <i class="pe-7s-comment"></i>
              </div>
              <h3 class="box-with-icon-title"><b>Asistente Personal</b></h3>
              <p><b>Será guiado a través de la aplicación con la ayuda de un asistente virtual.</b></p>
            </div><!-- .box-with-icon -->
          </div><!-- .col-sm-4 -->
          <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-icon text-center">
              <div class="box-with-icon-icon">
                <i class="pe-7s-date"></i>
              </div>
              <h3 class="box-with-icon-title"><b>Calendario de Eventos</b></h3>
              <p><b>Podrás saber si se realizarán eventos en fechas próximas.</b></p>
            </div><!-- .box-with-icon -->
          </div><!-- .col-sm-4 -->
            <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-icon text-center">
              <div class="box-with-icon-icon">
                <i class="pe-7s-config"></i>
              </div>
              <h3 class="box-with-icon-title"><b>Configuraciones personalizadas</b></h3>
              <p><b>Podrá configurar las opciones de la aplicación a su preferencia.</b></p>
            </div><!-- .box-with-icon -->
          </div>
        </div><!-- .row -->
      </div><!-- .container -->
    </section>

    <!-- Video Popup -->
    <section class="fw-section parallax text-center padding-top-3x padding-bottom-3x" style="background-image: url(../../img/home/video-bg.jpg);" data-stellar-background-ratio="0.65">
      <span class="overlay" style="opacity: .78;"></span>
      <div class="container padding-top-3x padding-bottom-3x">
        <!-- Remove ".light-skin" class to alter appearance. -->
        <a href="http://wideo.co/es/view/19048401487664921565-videopromocional" class="video-popup-btn light-skin space-top-3x space-bottom">
          <i class="pe-7s-film"></i>
        </a>
        <p class="text-lg text-light space-bottom-2x"><h5><b>Ver video de introducción a la aplicación</b></h5></p>
      </div><!-- .container -->
    </section><!-- .fw-section -->

    <!-- Testimonials Carousel -->
    <!-- Data API:
      data-loop="true/false" enable/disable looping
      data-autoplay="true/false" enable/disable carousel autoplay
      data-interval="3000" autoplay interval timeout in miliseconds
      Simply add necessary data attribute to the ".testimonials-carousel" with appropriate value to adjust carousel functionality.
     -->
    <section class="container padding-top-3x padding-bottom-3x" id="comentarioss">
      <h2 class="block-title text-center"><b>Comentarios</b></h2>
      <div class="testimonials-carousel" data-loop="true">
        <div class="inner">
          <div class="testimonial">
            <div class="testimonial-author-ava">
              <img src="../../img/testimonials/01.jpg" alt="Jessie Pinkman"/>
            </div>
            <h3 class="testimonial-author-name"><b>Samuel Corona</b></h3>
            <p><b>Excelente aplicación, el listado de sitios por visitar está bastante completo.</b></p>
            <!--<img src="../../img/testimonials/logo01.png" alt="MailChimp"/>-->
          </div><!-- .testimonial -->
          <div class="testimonial">
            <div class="testimonial-author-ava">
              <img src="../../img/testimonials/02.jpg" alt="Lara Goodwill"/>
            </div>
            <h3 class="testimonial-author-name"><b>Armando Cruz</b></h3>
            <p><b>Fue la primera vez que salí de Taumaulipas y mi experiencia en Mérida fue excelente gracias a esta aplicación, altamente recomendable para aquellos que como yo, gustan de la cultura de otros estados.</b></p>
          </div>
                <!-- .testimonial -->
          <div class="testimonial">
            <div class="testimonial-author-ava">
              <img src="../../img/testimonials/03.jpg" alt="Miike Johnson"/>
            </div>
            <h3 class="testimonial-author-name"><b>Jonathan Cuamatzi</b></h3>
            <p><b>Muy buena aplicación, ya la he recomendado a todos mis amigos.</b></p>
          </div><!-- .testimonial -->
        </div><!-- .inner -->
      </div><!-- .testimonials-carousel -->
    </section><!-- .container.padding-bottom-3x -->




    <section class="fw-section parallax text-center padding-top-3x padding-bottom-3x" style="background-image: url(../../img/home/video-bg.jpg);" data-stellar-background-ratio="0.65" id="theteam">
      <div class="pricing-header" style="background-image: url(../../img/home/pricing-bg.jpg);" data-stellar-background-ratio="0.65">

      <span class="overlay" style="opacity: .78;"></span>
          </div>
        <div class="container space-bottom">
        <div class="pricing-table">
          <h2 class="block-title text-center text-light"><b>Nuestro equipo</b>
            <small><b>Gente inteligente. Ideas geniales. Resultados sobresalientes</b></small>
          </h2>
            </div>
          <div>


              <div class="row">
          <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-image text-center">
              <div class="box-with-image-image">
                <img src="../../img/team/01.jpg" alt="Johnathan Doe">
              </div>
              <h3 class="box-with-image-title"><b>Joseph Alonzo</b>
                <small><b>CEO, Fundador</b></small>
              </h3>
              <!--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>-->
              <div class="social-bar text-center">
                <a href="#" class="sb-facebook" data-toggle="tooltip" data-placement="top" title="Facebook">
                  <i class="fa fa-facebook"></i>
                </a>
                <a href="#" class="sb-twitter" data-toggle="tooltip" data-placement="top" title="Twitter">
                  <i class="fa fa-twitter"></i>
                </a>
                <a href="#" class="sb-google-plus" data-toggle="tooltip" data-placement="top" title="Google+">
                  <i class="fa fa-google-plus"></i>
                </a>
              </div>
            </div><!-- .box-with-image -->
          </div><!-- .col-sm-4 -->
          <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-image text-center">
              <div class="box-with-image-image">
                <img src="../../img/team/02.jpg" alt="Anabelle Dorris">
              </div>
              <h3 class="box-with-image-title"><b>Susana Ku</b>
                <small><b>Analísta de Sistemas</b></small>
              </h3>
              <!--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>-->
              <div class="social-bar text-center">
                <a href="#" class="sb-facebook" data-toggle="tooltip" data-placement="top" title="Facebook">
                  <i class="fa fa-facebook"></i>
                </a>
                <a href="#" class="sb-google-plus" data-toggle="tooltip" data-placement="top" title="Google+">
                  <i class="fa fa-google-plus"></i>
                </a>
                <a href="#" class="sb-instagram" data-toggle="tooltip" data-placement="top" title="Instagram">
                  <i class="fa fa-instagram"></i>
                </a>
              </div>
            </div><!-- .box-with-image -->
          </div><!-- .col-sm-4 -->
          <div class="col-sm-4">
            <!-- Use ".light-skin" class to alter appearance. -->
            <div class="box-with-image text-center">
              <div class="box-with-image-image">
                <img src="../../img/team/03.jpg" alt="Taylor White"/>
              </div>
              <h3 class="box-with-image-title"><b>Ariel Keb</b>
                <small><b>Diseñador encargado</b></small>
              </h3>
              <!--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>-->
              <div class="social-bar text-center">
                <a href="#" class="sb-facebook" data-toggle="tooltip" data-placement="top" title="Facebook">
                  <i class="fa fa-facebook"></i>
                </a>
                <a href="#" class="sb-twitter" data-toggle="tooltip" data-placement="top" title="Twitter">
                  <i class="fa fa-twitter"></i>
                </a>
                <a href="#" class="sb-google-plus" data-toggle="tooltip" data-placement="top" title="Google+">
                  <i class="fa fa-google-plus"></i>
                </a>
              </div>
            </div><!-- .box-with-image -->
          </div><!-- .col-sm-4 -->
        </div>


          </div>

          </div>
      
    </section>
        


    <!-- Contacts -->
    <section class="container" id="lecontact">
        <h2 class="block-title text-center"></h2>
      <h2 class="text-center space-bottom-2x"><b>Contáctanos</b></h2>
      <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
          <div class="row">
            <div class="col-sm-4">
              <div class="tile tile-with-icon">
                <div class="tile-body">
                  <i class="pe-7s-map-marker"></i>
                  <h4><b>Dirección</b></h4>
                  <p><b>Calle 115 #404 x 50 Col. Santa Rosa</b></p>
                    <br />
                </div>
              </div><!-- .tile.tile-with-icon -->
            </div><!-- .col-sm-4 -->
            <div class="col-sm-4">
              <div class="tile tile-with-icon">
                <div class="tile-body">
                  <i class="pe-7s-call"></i>
                  <h4><b>Teléfono</b></h4>
                  <p><b>+52 (999) 3 70 99 73</b></p>
                  <br>
                </div>
              </div><!-- .tile.tile-with-icon -->
            </div><!-- .col-sm-4 -->
            <div class="col-sm-4">
              <div class="tile tile-with-icon">
                <div class="tile-body">
                  <i class="pe-7s-mail"></i>
                  <h4><b>Email</b></h4>
                  <p><a href="mailto:new_york@mail"><b>MagnetValkyrion@gmail.com</b></a><br><br></p>
                </div>
              </div><!-- .tile.tile-with-icon -->
            </div><!-- .col-sm-4 -->
          </div><!-- .row -->
        </div><!-- .col-lg-10.col-lg-offset-1 -->
      </div><!-- .row -->
    </section><!-- .container -->
</asp:Content>
