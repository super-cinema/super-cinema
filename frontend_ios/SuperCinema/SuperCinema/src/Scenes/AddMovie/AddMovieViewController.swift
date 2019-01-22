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
        let year = yearTextField.text != nil ? Int(yearTextField.text!) : nil
        let movie = Movie(title: title,
                          duration: Int(duration)!,
                          productionCountry: countryTextField.text,
                          productionYear: year,
                          directors: [],
                          types: [])
        Current.dataProvider.postMovie(movie) { [weak self] result in
            self?.handleResult(result)
        }
    }

    private func handleResult(_ result: Result<Any>) {
        switch result {
        case .error(let error):
            print(error)
        case .succes(_):
            dismiss(animated: true, completion: nil)
        }
    }

    @objc private func cancel() {
        dismiss(animated: true, completion: nil)
    }

}

