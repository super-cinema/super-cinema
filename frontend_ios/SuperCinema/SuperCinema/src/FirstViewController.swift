//
//  FirstViewController.swift
//  SuperCinema
//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {

    var task: URLSessionTask?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.

        let url = URL(string: "http://localhost:8080/movie")!
        task = URLSession.shared.dataTask(with: url,
                                          completionHandler: { (data, response, error) in
                                            print("finished")
                                            if let data = data {
                                                let result = String(data: data, encoding: .utf8)
                                                print(result)
                                            }
                                            if let error = error {
                                                print(error)
                                            }
        })
        task?.resume()
    }


}

