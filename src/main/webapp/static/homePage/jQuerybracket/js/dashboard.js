jQuery(document).ready(function(){
	
	"use strict";
	
	function showTooltip(x, y, contents) {
		jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css( {
		  position: 'absolute',
		  display: 'none',
		  top: y + 5,
		  left: x + 5
		}).appendTo("body").fadeIn(200);
	}
    
 
    
   
    
   jQuery('#sparkline').sparkline([4,3,3,1,4,3,2,2,3,10,9,6], {
		  type: 'bar', 
		  height:'30px',
        barColor: '#428BCA'
   });
	
    
    jQuery('#sparkline2').sparkline([9,8,8,6,9,10,6,5,6,3,4,2], {
		  type: 'bar', 
		  height:'30px',
        barColor: '#999'
    });
    
    // Chosen Select
//    jQuery("select").chosen({
 //     'min-width': '100px',
 //     'white-space': 'nowrap',
  //    disable_search_threshold: 10
 //   });
	 
	
	// Do not use the code below. It's for demo purposes only
	var c = jQuery.cookie('change-skin');
   if (jQuery('.panel-stat').length > 0 && c == 'dodgerblue') {
      jQuery('.panel-stat').each(function(){
         if ($(this).hasClass('panel-danger')) {
            $(this).removeClass('panel-danger').addClass('panel-warning');
         }
      });
   }
	
	if (jQuery('#basicflot').length > 0 && c == 'greyjoy') {
      plot.setData([{
			data: uploads,
			color: '#dd5702',
			label: 'Uploads',
			lines: {
				show: true,
				fill: true,
				lineWidth: 1
			},
			splines: {
				show: false
			}
		},
		{
			data: downloads,
			color: '#cc0000',
			label: 'Downloads',
			lines: {
				show: true,
				fill: true,
				lineWidth: 1
			},
			splines: {
				show: false
			}
		}]);
		plot.draw();
   }
    
});

function chart1(){
	 var uploads = [ [5, 0.5], [6, 0.6], [7, 0.55], [8, 0.58], [9, 0.89], [10, 0.89], [11, 0.96], [12, 0.98], [1, 0.2], [2, 0.3]];
		
	
	var plot = jQuery.plot(jQuery("#basicflot"),
		[{ data: uploads,
         label: "使用率",
         color: "#1CAF9A"
        }
       
      ],
      {
			series: {
				lines: {
					show: false
				},
				splines: {
					show: true,
					tension: 1,
					lineWidth: 1,
					fill: 0.45
				},
				shadowSize: 0
			},
			points: {
				show: true
			},
		  legend: {
          position: 'nw'
        },
		  grid: {
          hoverable: true,
          clickable: true,
          borderColor: '#ddd',
          borderWidth: 1,
          labelMargin: 10,
          backgroundColor: '#fff'
        },
		  yaxis: {
          color: '#eee'
        },
        xaxis: {
          color: '#eee'
        }
		});
		
	 var previousPoint = null;
	 jQuery("#basicflot").bind("plothover", function (event, pos, item) {
     jQuery("#x").text(pos.x.toFixed(2));
     jQuery("#y").text(pos.y.toFixed(2));
			
		
	 });
		
	 jQuery("#basicflot").bind("plotclick", function (event, pos, item) {
		if (item) {
		  plot.highlight(item.series, item.datapoint);
		}
	 });
}

function chart2(){
	 // Donut Chart
	   var m1 = new Morris.Donut({
	        element: 'donut-chart2',
	        data: [
	          {label: "自筹", value: 30},
	          {label: "技改", value: 20},
	          {label: "型号", value: 20},
	          {label: "国有", value: 20},
	          {label: "其他", value: 10}
	        ],
	        colors: ['#D9534F','#1CAF9A','#428BCA','#5BC0DE','#428BCA']
	    });
	    
	    
	   var m2 = new Morris.Line({
	        // ID of the element in which to draw the chart.
	        element: 'line-chart',
	        // Chart data records -- each entry in this array corresponds to a point on
	        // the chart.
	        data: [
	            { y: '2015', a: 10, b: 100 },
	            { y: '2016', a: 20,  b: 90 },
	            { y: '2017', a: 45,  b: 65 },
	            { y: '2018', a: 58,  b: 52 },
	            { y: '2019', a: 80,  b: 30 },
	            { y: '2020', a: 95,  b: 20 },
	            { y: '2021', a: 105, b: 5 }
	        ],
	        xkey: 'y',
	        ykeys: ['a', 'b'],
	        labels: ['Series A', 'Series B'],
	        gridTextColor: 'rgba(255,255,255,0.5)',
	        lineColors: ['#fff', '#fdd2a4'],
	        lineWidth: '2px',
	        hideHover: 'always',
	        smooth: false,
	        grid: false
	   });
		
		// Trigger Resize in Morris Chart
	   var delay = (function() {
			var timer = 0;
			return function(callback, ms) {
				clearTimeout(timer);
				timer = setTimeout(callback, ms);
			};
	    })();

	   jQuery(window).resize(function() {
			delay(function() {
				m1.redraw();
				m2.redraw();
		}, 200);
	   }).trigger('resize');
}
