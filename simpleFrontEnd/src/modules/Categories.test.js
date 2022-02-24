import { render, screen } from '@testing-library/react';
import { Categories } from './Categories'

describe('CategoryListShould', ()=>{

    beforeEach(()=>{
        render(<Categories />);
    })

    it('render the expected items on the page', ()=>{
        const title = screen.getByText('Categories');
        const button = screen.getByRole('button');
        const newCategoryForm = screen.queryByRole('form', {accessibleName: 'categoryForm'});

        expect(button).toBeInTheDocument();
        expect(title).toBeInTheDocument();

        expect(newCategoryForm).not.toBeInTheDocument();
    })

    it('render category form when new button clicked', ()=> {
        const button = screen.getByRole('button');
        button.click();

        const newCategoryForm = screen.getByRole('form', {accessibleName: 'categoryForm'});

        expect(newCategoryForm).toBeInTheDocument();

    })

    it('render expected input in the form', () =>{
        const button = screen.getByRole('button');
        button.click();

        const name = screen.getByLabelText('Name:')
        const description = screen.getByLabelText('Description:')
        const picture = screen.getByLabelText('Picture:')

        expect(name).toBeInTheDocument()
        expect(description).toBeInTheDocument()
        expect(picture).toBeInTheDocument()

    })

})