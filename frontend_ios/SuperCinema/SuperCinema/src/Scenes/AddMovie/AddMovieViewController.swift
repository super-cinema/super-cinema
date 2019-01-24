//
//  Created by Krzysztof Pawski on 20/01/2019.
//  Copyright © 2019 SuperCinemaSpZoo. All rights reserved.
//

enum MovieType: String {
    case COMEDY = "comedy"
    case HORROR = "horror"
    case SF = "sf"
    case ACTION = "action"
    case THRILLER = "thriller"
    case DRAMA = "drama"
    case CRIME = "crime"
    case FANTASY = "fantasy"
    case MUSIC = "music"
    case ANIMATON = "animation"
    case WESTERN = "western"
}


import UIKit

final class AddMovieViewController: UIViewController {

    @IBOutlet weak private var titleTextField: UITextField!
    @IBOutlet weak private var durationTextField: UITextField!
    @IBOutlet weak private var yearTextField: UITextField!
    @IBOutlet weak private var countryTextField: UITextField!

    private var task: URLSessionTask?
    private var yearPickerDataSource: PickerDataSource?

    override func viewDidLoad() {
        super.viewDidLoad()

        let doneMoviewItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(addMovie))
        navigationItem.rightBarButtonItem = doneMoviewItem

        let cancelMovieItem = UIBarButtonItem(barButtonSystemItem: .cancel, target: self, action: #selector(cancel))
        navigationItem.leftBarButtonItem = cancelMovieItem

        let years = Array(1900...2019).map { String($0) }
        yearPickerDataSource = PickerDataSource(values: years, textField: yearTextField)

        let pickerView = UIPickerView()
        pickerView.dataSource = yearPickerDataSource
        pickerView.delegate = yearPickerDataSource
        yearTextField.inputView = pickerView
        pickerView.selectRow(100, inComponent: 0, animated: false)

        let toolbar = toolbarPiker(mySelect: #selector(dismissPicker))
        yearTextField.inputAccessoryView = toolbar

    }

    @objc private func dismissPicker() {
        view.endEditing(true)
    }

    private func toolbarPiker(mySelect : Selector) -> UIToolbar {

        let toolBar = UIToolbar()

        toolBar.barStyle = .default
        toolBar.sizeToFit()

        let doneButton = UIBarButtonItem(title: "Done", style: .plain, target: self, action: mySelect)
        let spaceButton = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)

        toolBar.setItems([ spaceButton, doneButton], animated: false)
        toolBar.isUserInteractionEnabled = true

        return toolBar
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

