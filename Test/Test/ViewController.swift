//
//  ViewController.swift
//  Test
//
//  Created by Albert Jin on 9/3/19.
//  Copyright © 2019 AJ Studio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var slide: UIImageView!

    @IBAction func Choose(_ sender: UIButton) {
        if sender.tag == 1{
            slide.image = UIImage(named: "greenlight")
        }
        else if sender.tag == 2{
            slide.image = UIImage(named: "bluelight")
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

