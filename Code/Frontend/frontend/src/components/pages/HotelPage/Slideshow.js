import React, { useState } from 'react'
import './Slider.css'
import BtnSlider from './BtnSlider'

function Slideshow(props) {
    let counter = 1;
    const slideImages = [];

    while (counter < 11) {
        slideImages.push({ url: process.env.PUBLIC_URL + '/img/' + props.name + " " + counter + '.jpg', caption: 'Slide' })
        counter++;
    }
    const [slideIndex, setSlideIndex] = useState(1)

    const nextSlide = () => {
        if (slideIndex !== slideImages.length) {
            setSlideIndex(slideIndex + 1)
        }
        else if (slideIndex === slideImages.length) {
            setSlideIndex(1)
        }
    }

    const prevSlide = () => {
        if (slideIndex !== 1) {
            setSlideIndex(slideIndex - 1)
        }
        else if (slideIndex === 1) {
            setSlideIndex(slideImages.length)
        }
    }

    return (
        <div className="container-slider">
            {slideImages.map((obj, index) => {
                return (
                    <div
                        className={slideIndex === index + 1 ? "slide active-anim" : "slide"}
                    >
                        <img
                            src={obj.url}
                        />
                    </div>
                )
            })}
            <BtnSlider moveSlide={nextSlide} direction={"next"} />
            <BtnSlider moveSlide={prevSlide} direction={"prev"} />
        </div>
    )
}

export default Slideshow
