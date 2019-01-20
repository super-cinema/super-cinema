//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

import UIKit

final class AddMovieViewController: UIViewController {

    @IBOutlet weak private var titleTextField: UITextField!
    @IBOutlet weak private var durationTextField: UITextField!
    @IBOutlet weak private var yearTextField: UITextField!
    @IBOutlet weak private var countryTextField: UITextField!

    private var task: URLSessionTask?

    override func viewDidLoad() {
        super.viewDidLoad()

        let doneMoviewItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(addMovie))
        navigationItem.rightBarButtonItem = doneMoviewItem

        let cancelMovieItem = UIBarButtonItem(barButtonSystemItem: .cancel, target: self, action: #selector(cancel))
        navigationItem.leftBarButtonItem = cancelMovieItem
    }

    @objc private func addMovie() {
        guard let title = titleTextField.text, let duration = durationTextField.text else { return }
        let movie = Movie(title: title,
                          duration: Int(duration)!,
                          productionCountry: countryTextField.text,
                          productionYear: yearTextField.text,
                          directors: [],
                          types: [])

        let url = URL(string: "http://localhost:8080/movie")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("Application/json", forHTTPHeaderField: "Content-Type")

        let encoder = JSONEncoder()
        if let body = try? encoder.encode(movie) {
            request.httpBody = body
        }

        task = URLSession.shared.dataTask(with: request) { [weak self] data, response, error in
            if let error = error {
                print(error)
                return
            }
            self?.dismiss(animated: true, completion: nil)
        }
        task?.resume()
    }

    @objc private func cancel() {
        dismiss(animated: true, completion: nil)
    }

}

